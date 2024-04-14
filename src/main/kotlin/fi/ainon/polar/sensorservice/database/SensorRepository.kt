package fi.ainon.polar.sensorservice.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EcgRepository: JpaRepository<EcgEntity, Long> {
}

@Repository
interface HrRepository: JpaRepository<HrEntity, Long> {
}
