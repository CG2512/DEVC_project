package jmaster.io.devc_ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmaster.io.devc_ui.client.service.WSBookService;

@Service
public class BookService {
	@Autowired
	WSBookService wsBookService;
	
}
