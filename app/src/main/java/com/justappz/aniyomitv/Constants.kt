package com.justappz.aniyomitv

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
}