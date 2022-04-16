package com.annevonwolffen.tfs.developerslife.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Модель объекта информации о гиф-изображении
 *
 * @author Terekhova Anna
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class GifModel @JsonCreator constructor(
    @param:JsonProperty("id")
    @get:JsonProperty("id")
    val id: Int,

    @param:JsonProperty("description")
    @get:JsonProperty("description")
    val description: String,

    @param:JsonProperty("gifURL")
    @get:JsonProperty("gifURL")
    val gifUrl: String,

    @param:JsonProperty("width")
    @get:JsonProperty("width")
    val width: Int?,

    @param:JsonProperty("height")
    @get:JsonProperty("height")
    val height: Int?,
)
