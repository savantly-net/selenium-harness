package net.savantly.selenium.harness.modules.view;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ViewEntityRepository extends PagingAndSortingRepository<ViewEntity, UUID> {

}
