package com.tdc.nhom8.appdoctruyentranhonline
data class Chapter(
    val id: Int,
    val comicId: Int,             // ID của Truyện mà Chapter thuộc về
    val title: String,            // Tên Chapter
    val content: String?,         // Nội dung (có thể null nếu là truyện tranh chỉ có hình ảnh)
    val chapterNumber: Int,       // Số thứ tự của Chapter
    val createdAt: String?,
    val updatedAt: String?
)
