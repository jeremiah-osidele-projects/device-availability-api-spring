package com.jeremiah.daapi.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jeremiah.daapi.entity.Device;
import com.jeremiah.daapi.entity.DeviceStatus;

import jakarta.persistence.LockModeType;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

	// one device where the assetId column matches the given value
	Optional<Device> findByAssetId(Integer assetId);

	// all devices that have a certain status
    List<Device> findByStatus(DeviceStatus status);

    // one device where the assetId column matches the given value, 
    // but with a pessimistic write lock. Manage concurrency - prevent hazzaerds like race conditions - only one update/write at a time, and no one else can read, until the lock is released
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select d from Device d where d.assetId = :assetId")
    Optional<Device> findByAssetIdForUpdate(@Param("assetId") Integer assetId);
}

//JpaSpecificationExecutor<Device>

//This allows to run complex, dynamic queries (e.g. filtering devices by multiple criteria at runtime).