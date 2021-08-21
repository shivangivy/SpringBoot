/**
 * 
 */
package com.electem.filesupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electem.filesupload.model.FileInfo;

/**
 * File Storage Repository
 *
 */
@Repository
public interface FileStorageRepository extends JpaRepository<FileInfo, Long>{

}
