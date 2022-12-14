package ua.cn.stu.converter;

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

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.CountDownLatch;

import javax.measure.converter.UnitConverter;

public class ConverterThread extends Thread {

    private static final String TAG = "MainGameHelperThread";
    private Handler handler;

    public static class ConverterCounting implements Runnable {

        private static final String TAG = "ConverterCounting";
        private final CountDownLatch latch;
        private String inputNumber;
        private String outputUnit;
        private String inputUnit;
        private float result;

        public ConverterCounting(CountDownLatch latch, String inputNumber, String outputUnit, String inputUnit) {
            this.latch = latch;
            this.inputNumber = inputNumber;
            this.outputUnit = outputUnit;
            this.inputUnit = inputUnit;
        }

        public float getResult() { return result; }

        @Override
        public void run() {
            Log.d(TAG, "The start of converting");
            result = computing();
            Log.d(TAG, "The result" + result);
            Log.d(TAG, "The end of converting");
            latch.countDown();
        }
        public float computing() {
            float value = 0;
            if(!inputNumber.isEmpty()) value = Float.parseFloat(inputNumber);
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
                        case "ft": {
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
                        case "ft": {
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
                        case "ft": {
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
                        case "ft": {
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
                        case "ft": {
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
                        case "ft": {
                            fromUnits = YARD.getConverterTo(FOOT);
                        } break;
                        default: result = value; break;
                    }
                    break;
                case "ft": switch (outputUnit){
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
                    case "ft": {
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

    public void addTask(Runnable runnable) {
        if (handler == null)
            handler = new Handler();
        handler.post(runnable);
    }

    @Override
    public void run() {
        Looper.prepare();
        Log.d(TAG, "Initializing looper");
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        };
        Looper.loop();
    }
}
