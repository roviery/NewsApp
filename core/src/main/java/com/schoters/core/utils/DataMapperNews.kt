package com.schoters.core.utils

import com.schoters.core.data.source.local.entity.NewsEntity
import com.schoters.core.data.source.remote.response.NewsResponse
import com.schoters.core.domain.model.News

object DataMapperNews {

    fun mapResponsesToDomain(input: List<NewsResponse>): List<News> =
        input.map {
            News(
                id = 0,
                name = it.source.name,
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                image = it.image,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                id = it.id,
                name = it.name,
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                image = it.image,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }

    fun mapDomainToEntity(input: News) =
        NewsEntity(
            id = input.id,
            name = input.name,
            author = input.author,
            title = input.title,
            description = input.description,
            url = input.url,
            image = input.image,
            publishedAt = input.publishedAt,
            content = input.content
        )

}