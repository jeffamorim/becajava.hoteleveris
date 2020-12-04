package br.hoteleveris.app.controller;

import br.hoteleveris.app.response.BaseResponse;

public class BaseController {

	public BaseResponse errorBase = new BaseResponse();

	public BaseController() {
		errorBase.statusCode = 500;
		errorBase.message = "Ops...ocorreu um erro. Contate o administrador!";

	}
}
