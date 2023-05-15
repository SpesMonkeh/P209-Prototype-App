package com.p209.dinero.core.common.navigation

const val ROOT_GRAPH_ROUTE = "root_graph"
const val HOME_GRAPH_ROUTE = "home_graph"
const val ONBOARDING_GRAPH_ROUTE = "onboarding_graph"

sealed class DineoNavGraph(val route: String) {
    object Root : DineoNavGraph(route = ROOT_GRAPH_ROUTE)

    object Home : DineoNavGraph(route = HOME_GRAPH_ROUTE)

    object Onboarding : DineoNavGraph(route = ONBOARDING_GRAPH_ROUTE)
}