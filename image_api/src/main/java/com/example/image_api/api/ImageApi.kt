package com.example.image_api.api

import com.example.image_api.models.GalleryResponse
import com.example.image_api.models.GalleryTagsResponse
import com.example.image_api.models.TagResponse
import com.example.image_api.param.Section
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageApi {
    @GET("gallery/{section}") //TODO: use path params
    suspend fun getGallery(
        @Path("section") section: Section,
        @Query("album_previews") albumPreviews: Boolean? = true
    ): Response<GalleryResponse>

    @GET("tags")
    suspend fun getTags(): Response<GalleryTagsResponse>

    @GET("gallery/t/{tag}")
    suspend fun getTag(
        @Path("tag") tag: String
    ): Response<TagResponse>
}