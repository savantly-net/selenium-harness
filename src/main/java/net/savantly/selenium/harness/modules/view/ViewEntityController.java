package net.savantly.selenium.harness.modules.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.savantly.selenium.harness.rest.BaseController;

@RestController
@RequestMapping("/viewers")
public class ViewEntityController extends BaseController<ViewEntity, ViewEntityRepository> {
	
	@RequestMapping("/matchTypes")
	public MatchType[] getMatchTypes(){
		return MatchType.values();
	}

}
