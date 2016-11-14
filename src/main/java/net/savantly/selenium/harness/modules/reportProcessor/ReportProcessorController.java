package net.savantly.selenium.harness.modules.reportProcessor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.savantly.selenium.harness.rest.BaseController;

@RestController
@RequestMapping("/reportProcessors")
public class ReportProcessorController extends BaseController<ReportProcessor, ReportProcessorRepository>{

}
