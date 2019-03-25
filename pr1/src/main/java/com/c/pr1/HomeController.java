package com.c.pr1;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		return "main";
	}
	
	
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/coin", method = RequestMethod.GET)
    public String coin(Locale locale, Model model)
    {
        return "coinpage";
    }

	@RequestMapping(value = "/coinresult", method = RequestMethod.GET)
    public String coinResult(Locale locale, Model model, HttpServletRequest hsr)
    {
        List<Coin> coins = new ArrayList();
        for(int i = 1; hsr.getParameter((new StringBuilder("Pi")).append(i).toString()) != null; i++)
        {
            int pi = Integer.parseInt(hsr.getParameter((new StringBuilder("Pi")).append(i).toString()));
            int n1 = Integer.parseInt(hsr.getParameter((new StringBuilder("Ni")).append(i).toString()));
            Coin coin = new Coin(pi, n1);
            coins.add(coin);
        }

        CoinExchanger coine = new CoinExchanger(Integer.parseInt(hsr.getParameter("T")), coins);
        List<Coin> resultmessageArray = coine.ExchangeMethod();
        model.addAttribute("total", resultmessageArray.get(0));
        model.addAttribute("resultMessage", resultmessageArray.get(1));
        return "coinResult";
    }


}
