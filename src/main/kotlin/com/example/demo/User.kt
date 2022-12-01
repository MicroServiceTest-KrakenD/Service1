package com.example.demo

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
open class User(
    @Id
    @Column(name = "name", nullable = false)
    open var name: String = ""
)