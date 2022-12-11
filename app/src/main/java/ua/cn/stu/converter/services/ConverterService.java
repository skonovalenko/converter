package ua.cn.stu.converter.services;

import static javax.measure.unit.NonSI.FAHRENHEIT;
import static javax.measure.unit.NonSI.FOOT;
import static javax.measure.unit.NonSI.INCH;
import static javax.measure.unit.NonSI.MILE;
import static javax.measure.unit.NonSI.POUND;
import static javax.measure.unit.NonSI.TON_US;
import static javax.measure.unit.NonSI.YARD;
import static javax.measure.unit.SI.CELSIUS;
import static javax.measure.unit.SI.CENTIMETER;
import static javax.measure.unit.SI.GRAM;
import static javax.measure.unit.SI.KELVIN;
import static javax.measure.unit.SI.KILOGRAM;
import static javax.measure.unit.SI.KILOMETER;
import static javax.measure.unit.SI.METER;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import javax.measure.converter.UnitConverter;

public class ConverterService extends Service {

    public ConverterBinder binder = new ConverterBinder();

    public ConverterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return binder;
    }

    public class ConverterBinder extends Binder {
        public ConverterService getBoundService() {
            return ConverterService.this;
        }
    }
    public float computing(String inputNumber, String outputUnit, String inputUnit) {
        long value = 0;
        if(!inputNumber.isEmpty()) value = Long.parseLong(inputNumber);
        UnitConverter fromUnits = null;
        boolean toConvert = true;
        float result = 0;
        switch (inputUnit){
            case "sm":
                switch (outputUnit){
                    case "sm":  {
                        toConvert = false;
                        result = value;
                    }
                    break;
                    case "m": {
                        fromUnits = CENTIMETER.getConverterTo(METER);
                    } break;
                    case "km": {
                        fromUnits = CENTIMETER.getConverterTo(KILOMETER);
                    } break;
                    case "in": {
                        fromUnits = CENTIMETER.getConverterTo(INCH);
                    } break;
                    case "mi": {
                        fromUnits = CENTIMETER.getConverterTo(MILE);
                    } break;
                    case "yd": {
                        fromUnits = CENTIMETER.getConverterTo(YARD);
                    } break;
                    case "foor": {
                        fromUnits = CENTIMETER.getConverterTo(FOOT);
                    } break;
                    default: result = value; break;
                }
                break;
            case "m":
                switch (outputUnit){
                    case "sm":  {
                        fromUnits = METER.getConverterTo(CENTIMETER);
                    }
                    break;
                    case "m": {
                        toConvert = false;
                        result = value;
                    } break;
                    case "km": {
                        fromUnits = METER.getConverterTo(KILOMETER);
                    } break;
                    case "in": {
                        fromUnits = METER.getConverterTo(INCH);
                    } break;
                    case "mi": {
                        fromUnits = METER.getConverterTo(MILE);
                    } break;
                    case "yd": {
                        fromUnits = METER.getConverterTo(YARD);
                    } break;
                    case "foor": {
                        fromUnits = METER.getConverterTo(FOOT);
                    } break;
                    default: result = value; break;
                }
                break;
            case "km":
                switch (outputUnit){
                    case "sm":  {
                        fromUnits = KILOMETER.getConverterTo(CENTIMETER);
                    }
                    break;
                    case "m": {
                        fromUnits = KILOMETER.getConverterTo(METER);
                    } break;
                    case "km": {
                        toConvert = false;
                        result = value;
                    } break;
                    case "in": {
                        fromUnits = KILOMETER.getConverterTo(INCH);
                    } break;
                    case "mi": {
                        fromUnits = KILOMETER.getConverterTo(MILE);
                    } break;
                    case "yd": {
                        fromUnits = KILOMETER.getConverterTo(YARD);
                    } break;
                    case "foor": {
                        fromUnits = KILOMETER.getConverterTo(FOOT);
                    } break;
                    default: result = value; break;
                }
                break;
            case "in":
                switch (outputUnit){
                    case "sm":  {
                        fromUnits = INCH.getConverterTo(CENTIMETER);
                    }
                    break;
                    case "m": {
                        fromUnits = INCH.getConverterTo(METER);
                    } break;
                    case "km": {
                        fromUnits = INCH.getConverterTo(KILOMETER);
                    } break;
                    case "in": {
                        toConvert = false;
                        result = value;
                    } break;
                    case "mi": {
                        fromUnits = INCH.getConverterTo(MILE);
                    } break;
                    case "yd": {
                        fromUnits = INCH.getConverterTo(YARD);
                    } break;
                    case "foor": {
                        fromUnits = INCH.getConverterTo(FOOT);
                    } break;
                    default: result = value; break;
                }
                break;
            case "mi":
                switch (outputUnit){
                    case "sm":  {
                        fromUnits = MILE.getConverterTo(CENTIMETER);
                    }
                    break;
                    case "m": {
                        fromUnits = MILE.getConverterTo(METER);
                    } break;
                    case "km": {
                        fromUnits = MILE.getConverterTo(KILOMETER);
                    } break;
                    case "in": {
                        fromUnits = MILE.getConverterTo(INCH);
                    } break;
                    case "mi": {
                        toConvert = false;
                        result = value;
                    } break;
                    case "yd": {
                        fromUnits = MILE.getConverterTo(YARD);
                    } break;
                    case "foor": {
                        fromUnits = MILE.getConverterTo(FOOT);
                    } break;
                    default: result = value; break;
                }
                break;
            case "yd":
                switch (outputUnit){
                    case "sm":  {
                        fromUnits = YARD.getConverterTo(CENTIMETER);
                    }
                    break;
                    case "m": {
                        fromUnits = YARD.getConverterTo(METER);
                    } break;
                    case "km": {
                        fromUnits = YARD.getConverterTo(METER);
                    } break;
                    case "in": {
                        fromUnits = YARD.getConverterTo(INCH);
                    } break;
                    case "mi": {
                        fromUnits = YARD.getConverterTo(MILE);
                    } break;
                    case "yd": {
                        toConvert = false;
                        result = value;
                    } break;
                    case "foor": {
                        fromUnits = YARD.getConverterTo(FOOT);
                    } break;
                    default: result = value; break;
                }
                break;
            case "foor": switch (outputUnit){
                case "sm":  {
                    fromUnits = FOOT.getConverterTo(CENTIMETER);
                }
                break;
                case "m": {
                    fromUnits = FOOT.getConverterTo(METER);
                } break;
                case "km": {
                    fromUnits = FOOT.getConverterTo(KILOMETER);
                } break;
                case "in": {
                    fromUnits = FOOT.getConverterTo(INCH);
                } break;
                case "mi": {
                    fromUnits = FOOT.getConverterTo(MILE);
                } break;
                case "yd": {
                    fromUnits = FOOT.getConverterTo(YARD);
                } break;
                case "foor": {
                    toConvert = false;
                    result = value;
                } break;
                default: result = value; break;
            }
                break;
            default: result = value; break;
        }
        switch (inputUnit){
            case "g":
                switch (outputUnit){
                    case "g":  {
                        toConvert = false;
                        result = value;
                    }
                    break;
                    case "kg": {
                        fromUnits = GRAM.getConverterTo(KILOGRAM);
                    } break;
                    case "ton": {
                        fromUnits = GRAM.getConverterTo(TON_US);
                    } break;
                    case "lb": {
                        fromUnits = GRAM.getConverterTo(POUND);
                    } break;
                    default: result = value; break;
                }
                break;
            case "kg":
                switch (outputUnit){
                    case "g":  {
                        fromUnits = KILOGRAM.getConverterTo(GRAM);
                    }
                    break;
                    case "kg": {
                        toConvert = false;
                        result = value;
                    } break;
                    case "ton": {
                        fromUnits = KILOGRAM.getConverterTo(TON_US);
                    } break;
                    case "lb": {
                        fromUnits = KILOGRAM.getConverterTo(POUND);
                    } break;
                    default: result = value; break;
                }
                break;
            case "ton":
                switch (outputUnit){
                    case "g":  {
                        fromUnits = TON_US.getConverterTo(GRAM);
                    }
                    break;
                    case "kg": {
                        fromUnits = TON_US.getConverterTo(KILOGRAM);
                    } break;
                    case "ton": {
                        toConvert = false;
                        result = value;
                    } break;
                    case "lb": {
                        fromUnits = TON_US.getConverterTo(POUND);
                    } break;
                    default: result = value; break;
                }
                break;
            case "lb":
                switch (outputUnit){
                    case "g":  {
                        fromUnits = POUND.getConverterTo(GRAM);
                    }
                    break;
                    case "kg": {
                        fromUnits = POUND.getConverterTo(KILOGRAM);
                    } break;
                    case "ton": {
                        fromUnits = POUND.getConverterTo(TON_US);
                    } break;
                    case "lb": {
                        toConvert = false;
                        result = value;
                    } break;
                    default: result = value; break;
                }
                break;
            default: result = value; break;
        }
        switch (inputUnit){
            case "K":
                switch (outputUnit){
                    case "K":  {
                        toConvert = false;
                        result = value;
                    }
                    break;
                    case "C": {
                        fromUnits = KELVIN.getConverterTo(CELSIUS);
                    } break;
                    case "F": {
                        fromUnits = KELVIN.getConverterTo(FAHRENHEIT);
                    } break;
                    default: result = value; break;
                }
                break;
            case "C":
                switch (outputUnit){
                    case "K":  {
                        fromUnits = CELSIUS.getConverterTo(KELVIN);
                    }
                    break;
                    case "C": {
                        toConvert = false;
                        result = value;
                    } break;
                    case "F": {
                        fromUnits = CELSIUS.getConverterTo(FAHRENHEIT);
                    } break;
                    default: result = value; break;
                }
                break;
            case "F":
                switch (outputUnit){
                    case "K":  {
                        fromUnits = FAHRENHEIT.getConverterTo(KELVIN);
                    }
                    break;
                    case "C": {
                        fromUnits = FAHRENHEIT.getConverterTo(CELSIUS);
                    } break;
                    case "F": {
                        toConvert = false;
                        result = value;
                    } break;
                    default: result = value; break;
                }
                break;
            default: result = value; break;
        }
        if(toConvert) result = (float) fromUnits.convert(value);
        Log.d("---------S--------", String.valueOf(result));
        return result;
    }
}