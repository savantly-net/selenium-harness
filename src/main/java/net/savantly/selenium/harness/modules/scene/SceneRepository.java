package net.savantly.selenium.harness.modules.scene;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SceneRepository extends PagingAndSortingRepository<SceneItem, UUID>{

}
