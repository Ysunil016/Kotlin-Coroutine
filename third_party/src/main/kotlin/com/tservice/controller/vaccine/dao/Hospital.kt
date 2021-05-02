package com.tservice.controller.vaccine.dao

data class Hospital(
    val name:String,
    val fee_type:String,
    val block_name:String,
    val from:String,
    val to:String,
    val sessions: List<CowinSession>
)
