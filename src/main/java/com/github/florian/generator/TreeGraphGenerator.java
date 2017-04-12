package com.github.florian.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.florian.graph.Vertex;
import com.github.florian.utils.Config;

/**
 * Created by zhidong.fzd on 17/4/1.
 */
public class TreeGraphGenerator extends SingleGraphGenerator {

    protected int               inDegree;
    private int                 depth;
    private int                 firstOutDegree;
    private int                 otherOutDegree;
    private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();
    private List<String>        firstLeaf = new ArrayList<String>();
    private int                 vertexID  = 0;

    public TreeGraphGenerator() {
        this(Config.getInt("generator.tree.depth", 5),
            Config.getInt("generator.tree.degree.first", 10),
            Config.getInt("generator.tree.degree.other", 10),
            Config.getInt("generator.tree.degree.in", 10));

    }

    public TreeGraphGenerator(int depth, int firstOutDegree, int otherOutDegree) {
        this(depth, firstOutDegree, otherOutDegree, 1);
    }

    public TreeGraphGenerator(int depth, int firstOutDegree, int otherOutDegree, int inDegree) {
        desc.setDirected(true);
        desc.setLayered(true);
        this.depth = depth;
        this.firstOutDegree = firstOutDegree;
        this.otherOutDegree = otherOutDegree;
        this.inDegree = inDegree;
    }

    private String genVertexKey() {
        String id = String.valueOf(vertexID++);
        final Vertex vertex = genVertex(id);
        vertexMap.put(id, vertex);
        return id;
    }

    /**
     * 产生outDegree个子节点
     * @param outDegree
     * @return
     */
    private List<String> genChildren(int outDegree) {
        List<String> children = new ArrayList<String>(outDegree);
        for (int j = 0; j < outDegree; j++) {
            final String child = genVertexKey();
            children.add(child);
        }

        return children;
    }

    /**
     * 链接两个点集合，形成边
     * @param roots
     * @param children
     */
    private void combineChildren(List<String> roots, List<String> children) {
        for (String root : roots) {
            for (String child : children) {
                genEdge(root, child);
            }
        }
    }

    /**
     * 逐级生成子树
     * @param roots
     * @param depth
     */
    private void genSubTree(List<String> roots, int depth) {
        int outDegree = (depth == 0 ? inDegree : (depth == 1 ? firstOutDegree : otherOutDegree));

        if (depth == 0) {
            // 生成子节点
            final List<String> children = genChildren(outDegree);

            // 返回根节点
            roots.clear();
            roots.addAll(children);

            // 生成下一级
            genSubTree(children, depth + 1);
        } else if (depth == 1) {
            // 生成子节点
            final List<String> children = genChildren(outDegree);

            // 链接节点
            combineChildren(roots, children);

            // 生成下一级
            genSubTree(children, depth + 1);
        } else if (depth > this.depth) {

            // 记录足够的叶子节点
            if (firstLeaf.size() < inDegree) {
                firstLeaf.addAll(roots);
            }

        } else {
            final int size = roots.size();
            for (int i = 0; i < size; i++) {
                // 生成子节点
                final List<String> children = genChildren(outDegree);

                // 循环选择父节点
                List<String> selectedRoots = new ArrayList<String>();
                for (int j = 0; j < inDegree; j++) {
                    int index = (i + j) % size;
                    selectedRoots.add(roots.get(index));
                }

                // 链接节点
                combineChildren(selectedRoots, children);

                // 生成下一级
                genSubTree(children, depth + 1);
            }

        }
    }

    protected void genEdge(String start, String end) {
        genEdge(vertexMap.get(start), vertexMap.get(end));
    }

    /**
     * 生成图测试数据
     * 点数／边数：inDegree * (firstOutDegree * (1 - otherOutDegree ^ depth) / (1 - otherOutDegree))
     * ~= inDegree * firstOutDegree * otherOutDegree ^ (depth - 1)
     */
    @Override
    protected boolean doGenerate() {
        if (depth < 1 || firstOutDegree < 0 || otherOutDegree < 0 || inDegree < 1) {
            LOG.error("invalid arguments");
            return false;
        }

        if (inDegree > firstOutDegree || inDegree > otherOutDegree) {
            LOG.error("invalid in degree");
            return false;
        }

        // 生成树根
        List<String> roots = new ArrayList<String>();
        genSubTree(roots, 0);

        return true;
    }

}
