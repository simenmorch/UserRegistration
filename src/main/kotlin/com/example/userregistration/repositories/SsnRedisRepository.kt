package com.example.userregistration.repositories

import com.example.userregistration.models.Ssn
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class SsnRedisRepository: CacheRepository<Ssn> {
    @Autowired
    private lateinit var redisRepository: RedisRepository

    private val storeKey = "SSN"

    override fun save(uuid: UUID, obj: Ssn) {
        redisRepository.operation().put(storeKey, uuid.toString(), obj.ssn)
    }

    override fun findByUuid(uuid: UUID): Ssn {
        val uuidString = redisRepository.operation().get(storeKey, uuid.toString()) as String

        return Ssn(uuidString)
    }

    override fun delete(uuid: UUID) {
        redisRepository.operation().delete(storeKey, uuid.toString())
    }
}