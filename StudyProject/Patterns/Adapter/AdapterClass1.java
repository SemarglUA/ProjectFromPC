package Patterns.Adapter;

/* Адаптация нескольких интерфейсов
Адаптировать IncomeData к Customer и Contact.
Классом-адаптером является IncomeDataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
Дополнить телефонный номер нулями до 10 цифр при необходимости (смотри примеры)
Обратите внимание на формат вывода фамилии и имени человека
*/

import java.util.HashMap;
import java.util.Map;

public class AdapterClass1 {
    public static Map<String, String> countries = new HashMap<String, String>();

    static
    {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args)
    {
        class Data implements IncomeData
        {

            @Override
            public String getCountryCode()
            {
                return "UA";
            }

            @Override
            public String getCompany()
            {
                return "Something";
            }

            @Override
            public String getContactFirstName()
            {
                return "Somebody";
            }

            @Override
            public String getContactLastName()
            {
                return "Somebody else";
            }

            @Override
            public int getCountryPhoneCode()
            {
                return 380;
            }

            @Override
            public int getPhoneNumber()
            {
                return 11111111;
            }
        }
        IncomeDataAdapter adapter = new IncomeDataAdapter(new Data());
        System.out.println(adapter.getCompanyName());
        System.out.println(adapter.getCountryName());
        System.out.println(adapter.getPhoneNumber());
        System.out.println(adapter.getName());
    }

    public static interface IncomeData
    {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567

    }

    public static interface Customer
    {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine

    }

    public static interface Contact
    {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67

    }

    //Тестовый main

    public static class IncomeDataAdapter implements Customer, Contact
    {
        private IncomeData incomeData;

        public IncomeDataAdapter(IncomeData incomeData)
        {
            this.incomeData = incomeData;
        }

        @Override
        public String getName()
        {
            return this.incomeData.getContactLastName() + ", " + this.incomeData.getContactFirstName();
        }

        @Override
        public String getPhoneNumber()
        {
            String clearPhoneNumber = Integer.valueOf(this.incomeData.getPhoneNumber()).toString();
            if (clearPhoneNumber.length() < 10)
            {
                while (clearPhoneNumber.length() < 10) clearPhoneNumber = "0" + clearPhoneNumber;
            }
            String newPhoneName = "+"
                    + this.incomeData.getCountryPhoneCode() + "(" + clearPhoneNumber.substring(0, 3)
                    + ")" + clearPhoneNumber.substring(3, 6) + "-" + clearPhoneNumber.substring(6, 8)
                    + "-" + clearPhoneNumber.substring(8);
            return newPhoneName;
        }

        @Override
        public String getCompanyName()
        {
            return this.incomeData.getCompany();
        }

        @Override
        public String getCountryName()
        {
            String countryName = "";
            for (Map.Entry<String, String> pair : countries.entrySet())
            {
                if (pair.getKey().equals(this.incomeData.getCountryCode())) countryName = pair.getValue();
            }
            return countryName;
        }
    }
}