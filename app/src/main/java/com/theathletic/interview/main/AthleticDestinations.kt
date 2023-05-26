package com.theathletic.interview.main

interface AthleticDestinations {
    val route: String
}

object Articles : AthleticDestinations {
    override val route = "articles"
}