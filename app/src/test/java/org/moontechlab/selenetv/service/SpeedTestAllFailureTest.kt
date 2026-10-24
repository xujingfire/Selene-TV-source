package org.moontechlab.selenetv.service

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.moontechlab.selenetv.model.SearchResult
import org.moontechlab.selenetv.model.SourceSpeed
import org.moontechlab.selenetv.model.SpeedTestState

class SpeedTestAllFailureTest {

    @Test
    fun invalid_url_reports_failed_instead_of_throwing() = runBlocking {
        val source = SearchResult(
            id = "1",
            source = "demo",
            episodes = listOf("not-a-url"),
        )
        val results = mutableMapOf<String, SourceSpeed>()

        SpeedTestService.testAll(listOf(source)) { key, result -> results[key] = result }

        assertEquals(SpeedTestState.FAILED, results["demo+1"]?.state)
    }
}
