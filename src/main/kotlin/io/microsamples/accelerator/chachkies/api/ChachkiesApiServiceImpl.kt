package io.microsamples.accelerator.chachkies.api

import io.microsamples.accelerator.chachkies.model.Chachkie
import io.microsamples.accelerator.chachkies.api.ChachkiesChachkieService
import org.jeasy.random.EasyRandom
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class ChachkiesApiServiceImpl : ChachkiesChachkieService {
    override fun getChachkies(): List<Chachkie> {
       return EasyRandom().objects(Chachkie::class.java, 13).toList()
    }
}