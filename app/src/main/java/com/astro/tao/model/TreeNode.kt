package com.astro.tao.model

class TreeNode(var name: String?, payloadSize: Int) {
    var payload: ByteArray?
    var children: MutableList<TreeNode?> = ArrayList<TreeNode?>()

    init {
        this.payload = ByteArray(payloadSize)
    }

    fun addChild(node: TreeNode?) {
        children.add(node)
    }
}