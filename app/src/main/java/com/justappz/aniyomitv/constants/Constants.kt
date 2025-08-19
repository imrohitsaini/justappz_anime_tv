package com.justappz.aniyomitv.constants

object Constants {
    const val POPULAR_ANIME_QUERY = """
        query(
            ${'$'}type: VaildPopularTypeEnumType!
            ${'$'}size: Int!
            ${'$'}page: Int
            ${'$'}dateRange: Int
        ) {
          queryPopular(
              type: ${'$'}type
              size: ${'$'}size
              dateRange: ${'$'}dateRange
              page: ${'$'}page
          ) {
            total
            recommendations {
              anyCard {
                _id
                name
                thumbnail
                englishName
                nativeName
                slugTime
              }
            }
          }
        }
    """

    const val EPISODE_QUERY = """
    query (${'$'}_id: String!) {
        show(
            _id: ${'$'}_id
        ) {
            _id
            thumbnail
            description
            type
            season
            score
            genres
            status
            studios
            availableEpisodesDetail
        }
    }
"""
}