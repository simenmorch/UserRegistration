package com.example.userregistration.services

import com.example.userregistration.models.fragments.Address
import com.example.userregistration.models.fragments.Personalia
import com.example.userregistration.models.fragments.Ssn
import com.example.userregistration.repositories.cache.AddressRedisRepository
import com.example.userregistration.repositories.cache.PersonaliaRedisRepository
import com.example.userregistration.repositories.cache.SsnRedisRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegistrationService {
    @Autowired
    private lateinit var ssnRepo: SsnRedisRepository
    @Autowired
    private lateinit var personaliaRepo: PersonaliaRedisRepository
    @Autowired
    private lateinit var addressRepo: AddressRedisRepository

    fun createRegistration(ssn: Ssn): UUID {
        val uuid = UUID.randomUUID()
        ssnRepo.save(uuid, ssn)

        return uuid
    }

    fun registerPersonalia(uuid: UUID, personalia: Personalia): Personalia {
        personaliaRepo.save(uuid, personalia)
        return personaliaRepo.findByUuid(uuid) // This redis read is not really necessary, but for now its a kind of validation that it actually persists.
    }

    fun registerAddress(uuid: UUID, address: Address): Address {
        addressRepo.save(uuid, address)
        return addressRepo.findByUuid(uuid)
    }

    fun finalizeRegistration(uuid: UUID) {

    }
}