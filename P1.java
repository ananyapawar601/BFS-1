//# Problem 1 - Binary Tree Level Order Traversal (https://leetcode.com/problems/binary-tree-level-order-traversal/)

//BFS Approach

// Time Complexity : O(n), Since there are n nodes in the tree, and each node is added to and removed from the queue once, the time complexity is O(n).
// Space Complexity : O(n), In the worst case (for a full binary tree), the last level may have approximately n/2 nodes, leading to O(n) space complexity.
//Additionally, we store O(n) elements in the result list. 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
//The approach uses BFS (Breadth-First Search) with a queue to traverse the tree level by level. 
//At each level, all nodes are processed, their values are stored in a list, and their children are added to the queue for the next level. 
//Finally, the lists of values for each level are collected into the result list and returned.


// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    // Function to perform level order traversal of a binary tree
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>(); // Stores the final level-wise traversal result
        if (root == null) return result; // If the tree is empty, return an empty list
        
        Queue<TreeNode> q = new LinkedList<>(); // Queue to facilitate level-order traversal
        q.add(root); // Start with the root node
        
        while (!q.isEmpty()) {
            int size = q.size(); // Number of nodes at the current level
            List<Integer> li = new ArrayList<>(); // List to store values at the current level
            
            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll(); // Remove the front node from the queue
                li.add(curr.val); // Add the node's value to the current level list

                // Add left child to the queue if it exists
                if (curr.left != null) {
                    q.add(curr.left);
                }
                // Add right child to the queue if it exists
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
            result.add(li); // Add the current level's values to the result list
        }
        return result; // Return the level order traversal result
    }
}


//DFS Approach using HashMaps

// Time Complexity : Each node is visited once → O(n)
// Space Complexity : O(n) for storing values in the HashMap. O(n) recursion stack in the worst case (skewed tree). Total: O(n).
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach - 
/*
 * Use DFS (Depth-First Search) to traverse the tree while maintaining a HashMap (levelMap) where the key is the level and the value is a list of node values at that level.
If a level does not exist in the map, create a new list for it.
Recursively process the left and right children, incrementing the level.
Finally, extract the values from the HashMap into a List<List<Integer>>.
 */

class Solution2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Map<Integer, List<Integer>> levelMap = new HashMap<>();
        traverse(root, 0, levelMap);

        // Convert HashMap values to a List of Lists
        return new ArrayList<>(levelMap.values());
    }

    private void traverse(TreeNode node, int level, Map<Integer, List<Integer>> levelMap) {
        if (node == null) return; // Base case
        
        // If the level is not in the map, create a new list
        levelMap.putIfAbsent(level, new ArrayList<>());

        // Add the current node value to its corresponding level
        levelMap.get(level).add(node.val);

        // Recursively process left and right children, increasing the level
        traverse(node.left, level + 1, levelMap);
        traverse(node.right, level + 1, levelMap);
    }
}


//DFS Recursive

// Time Complexity : Each node is visited once → O(n)
// Space Complexity : O(n) (in the worst case, for the recursion stack in a skewed tree). O(log n) (in a balanced tree).
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach - 
/*
 * The approach uses DFS (Depth-First Search) to traverse the tree while maintaining a list for each level. 
 * If a level is encountered for the first time, a new list is created. 
 * Each node’s value is added to its corresponding level list, 
 * and the function recursively processes left and right children with an incremented level.
 */

class Solution3 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>(); // Stores the final level-order traversal result
        dfs(root, 0, result); // Start DFS traversal from the root at level 0
        return result; // Return the list of levels
    }

    private void dfs(TreeNode root, int level, List<List<Integer>> result) {
        // Base case: If the node is null, return
        if (root == null) return;

        // If this is the first time visiting this level, create a new list for it
        if (result.size() == level) {
            result.add(new ArrayList<>());
        }

        // Add the current node's value to its corresponding level list
        result.get(level).add(root.val);

        // Recur for left and right children, increasing the level
        dfs(root.left, level + 1, result);
        dfs(root.right, level + 1, result);
    }
}
