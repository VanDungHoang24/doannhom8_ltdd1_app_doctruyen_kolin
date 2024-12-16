package com.tdc.nhom8.appdoctruyentranhonline

class Object {
    // Data classes for Author, User, and Comic
data class Author(val id: String, var name: String, var bio: String, var avatar: String)


    data class User(
        val id: String,
        var name: String,
        var password: String,
        var email: String,
        var User_cover_image: String
    )

    data class Comic(
        val id: String,
        var name: String,
        var author: Author,
        var price: Double,
        var cover_image: String,
        var description: String
    )

}