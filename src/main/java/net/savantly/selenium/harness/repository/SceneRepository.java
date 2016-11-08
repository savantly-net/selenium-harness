package net.savantly.selenium.harness.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import net.savantly.selenium.harness.domain.scene.SceneItem;

public interface SceneRepository extends PagingAndSortingRepository<SceneItem, UUID>{

}
