package com.github.appocalypse.rest

import org.apache.http.client.fluent.Request

class ValidateHtml {

	static main(args) {
		final def cli = new CliBuilder(usage: 'ValidateHtml')
		cli.h( longOpt: 'help', required: false, 'show usage information' )
		cli.u( longOpt: 'url', argName: 'u', required: true, args: 1, 'absolute url path i.e. http://google.com' )
		
		//--------------------------------------------------------------------------
		final def opt = cli.parse(args)
		if (!opt) { return }
		if (opt.h) {
			cli.usage();
			return
		}
		
		//--------------------------------------------------------------------------
		final def url = opt.u
		final def w3ValidatorApi = "http://validator.w3.org/check?uri=${url}"
		final def apiHeaderStatus = 'x-w3c-validator-status'
		
		def response = Request.Get(w3ValidatorApi).execute().returnResponse().getFirstHeader(apiHeaderStatus)
		println response
	}
}
