package app.ice.harmoniqa.data.model.home

import app.ice.harmoniqa.data.model.explore.mood.Mood
import app.ice.harmoniqa.data.model.home.chart.Chart
import app.ice.harmoniqa.utils.Resource

data class HomeDataCombine(
    val home: Resource<ArrayList<HomeItem>>,
    val mood: Resource<Mood>,
    val chart: Resource<Chart>,
    val newRelease: Resource<ArrayList<HomeItem>>,
) {
}