package br.com.erudio.controllers;

import br.com.erudio.exceptions.UnsuportedMathOperationException;
import br.com.erudio.mathoperations.NumberConverter;
import br.com.erudio.mathoperations.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

    @RestController
    public class MathController {

        private final AtomicLong counter = new AtomicLong();
        private final SimpleMath math = new SimpleMath();

        @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
        private Double sum(
                @PathVariable(value = "numberOne") String numberOne,
                @PathVariable(value = "numberTwo") String numberTwo
        ) throws UnsuportedMathOperationException {

            if(!NumberConverter.isnumeric(numberOne) || !NumberConverter.isnumeric(numberTwo)){
                throw new UnsuportedMathOperationException("Please set a numeric value!");

            }
            return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
        }

        @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}")
        public Double subtraction(
                @PathVariable(value = "numberOne") String numberOne,
                @PathVariable(value = "numberTwo") String numberTwo
        ) throws UnsuportedMathOperationException {

            if(!NumberConverter.isnumeric(numberOne) || !NumberConverter.isnumeric(numberTwo)){
                throw new UnsuportedMathOperationException("Please set a numeric value!");
            }

            return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
        }

        @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}")
        public Double multiplication(
                @PathVariable(value = "numberOne") String numberOne,
                @PathVariable(value = "numberTwo") String numberTwo
        ){
            if(!NumberConverter.isnumeric(numberOne) || !NumberConverter.isnumeric(numberTwo)){
                throw new UnsuportedMathOperationException("Please set a numeric value!");
            }

            return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
        }

        @RequestMapping(value = "/division/{numberOne}/{numberTwo}")
        public Double division(
                @PathVariable(value = "numberOne") String numberOne,
                @PathVariable(value = "numberTwo") String numberTwo
        ) throws UnsuportedMathOperationException{

            if(!NumberConverter.isnumeric(numberOne) || !NumberConverter.isnumeric(numberTwo)){
                throw new UnsuportedMathOperationException("Please set a numeric value");
            }
            return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
        }

        @RequestMapping(value = "/average/{numberOne}/{numberTwo}")
        public Double average(
                @PathVariable(value = "numberOne") String numberOne,
                @PathVariable(value = "numberTwo") String numberTwo
        ){
            if(!NumberConverter.isnumeric(numberOne) || !NumberConverter.isnumeric(numberTwo)){
                throw new UnsuportedMathOperationException("Please set a numeric value!");
            }

            return math.average(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
        }

        @RequestMapping(value = "squareroot/{numberOne}")
        public Double squareroot(
                @PathVariable(value = "numberOne") String numberOne
        ) throws UnsuportedMathOperationException{

            if(!NumberConverter.isnumeric(numberOne)){
                throw new UnsuportedMathOperationException("Please set a numeric value!");
            }

            return math.squareroot(NumberConverter.convertToDouble(numberOne));
        }

    }
