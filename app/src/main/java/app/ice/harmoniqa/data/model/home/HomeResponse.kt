package app.ice.harmoniqa.data.model.home

import app.ice.harmoniqa.data.model.explore.mood.Mood
import app.ice.harmoniqa.data.model.home.chart.Chart
import app.ice.harmoniqa.utils.Resource


data class HomeResponse(
    val homeItem: Resource<ArrayList<HomeItem>>,
    val exploreMood: Resource<Mood>,
    val exploreChart: Resource<Chart>
)