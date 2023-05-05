package ua.step.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.step.model.FractionResponse;


/*
I used GetMapping for fast testing.
 */

@Controller
@RequestMapping(value = "/fraction", produces = "application/json", headers = "Accept=*/*")
public class FractionController {

    @GetMapping("/is-correct")
    @ResponseBody
    public String isCorrect(@RequestParam long numerator, @RequestParam long denominator) {
        if(denominator == 0) return String.valueOf(false);

        long numeratorModule = numerator < 0 ? -1 * numerator : numerator;
        long denominatorModule = denominator < 0 ? -1 * denominator : denominator;

        return String.valueOf(numeratorModule < denominatorModule);
    }


    @GetMapping(value = "/reduction")
    @ResponseBody
    public String fractionReduction(@RequestParam long numerator, @RequestParam long denominator) {
        return FractionResponse.Json(numerator, denominator);
    }

    @GetMapping(value = "/sum")
    @ResponseBody
    public String sum
    (
            @RequestParam long numerator1, @RequestParam long denominator1,
            @RequestParam long numerator2, @RequestParam long denominator2
    ) {
        long numerator = numerator1 * denominator2 + numerator2 * denominator1;
        long denominator = denominator1 * denominator2;
        return FractionResponse.Json(numerator, denominator);
    }

    @GetMapping(value = "/subtraction")
    @ResponseBody
    public String subtraction(
            @RequestParam long numerator1, @RequestParam long denominator1,
            @RequestParam long numerator2, @RequestParam long denominator2
    ) {
        long numerator = numerator1 * denominator2 - numerator2 * denominator1;
        long denominator = denominator1 * denominator2;
        return FractionResponse.Json(numerator, denominator);
    }

    @GetMapping(value = "/multiplication")
    @ResponseBody
    public String multiplication(
            @RequestParam long numerator1, @RequestParam long denominator1,
            @RequestParam long numerator2, @RequestParam long denominator2
    ) {
        long numerator = numerator1 * numerator2;
        long denominator = denominator1 * denominator2;
        return FractionResponse.Json(numerator, denominator);
    }

    @GetMapping(value = "/division")
    @ResponseBody
    public String division(
            @RequestParam long numerator1, @RequestParam long denominator1,
            @RequestParam long numerator2, @RequestParam long denominator2
    ) {
        long numerator = numerator1 * denominator2;
        long denominator = denominator1 * numerator2;
        return FractionResponse.Json(numerator, denominator);
    }

}
