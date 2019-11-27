package com.unisystems.registry.tasks;

public class DifficultyJson {
    public static String json1 = "{\n" +
            "    \"taskResponse\": [\n" +
            "        {\n" +
            "            \"id\": 3,\n" +
            "            \"title\": \"New database\",\n" +
            "            \"desc\": \"Integrate sql db.\",\n" +
            "            \"difficulty\": \"EASY\",\n" +
            "            \"status\": \"New\",\n" +
            "            \"assignedEmployees\": [\n" +
            "                {\n" +
            "                    \"id\": 1,\n" +
            "                    \"recordNumber\": 111500001,\n" +
            "                    \"fullName\": \"Konstantinos Tsiaras\",\n" +
            "                    \"telephone\": \"6980429197\",\n" +
            "                    \"workingPeriod\": \"2019-11-02 - present\",\n" +
            "                    \"contractType\": \"Company Internal\",\n" +
            "                    \"position\": \"Programmer\",\n" +
            "                    \"status\": \"active\",\n" +
            "                    \"unitName\": \"Core Banking\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"updates\": []\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 4,\n" +
            "            \"title\": \"New database4\",\n" +
            "            \"desc\": \"Integrate sql db4.\",\n" +
            "            \"difficulty\": \"EASY\",\n" +
            "            \"status\": \"New\",\n" +
            "            \"assignedEmployees\": [\n" +
            "                {\n" +
            "                    \"id\": 3,\n" +
            "                    \"recordNumber\": 111500003,\n" +
            "                    \"fullName\": \"Steve Rogers\",\n" +
            "                    \"telephone\": \"6912345678\",\n" +
            "                    \"workingPeriod\": \"2012-06-22 - present\",\n" +
            "                    \"contractType\": \"External\",\n" +
            "                    \"position\": \"Scrum Master\",\n" +
            "                    \"status\": \"active\",\n" +
            "                    \"unitName\": \"Payment\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 4,\n" +
            "                    \"recordNumber\": 111500004,\n" +
            "                    \"fullName\": \"Lecter Hannibal\",\n" +
            "                    \"telephone\": \"6987654321\",\n" +
            "                    \"workingPeriod\": \"2018-03-15 - present\",\n" +
            "                    \"contractType\": \"Company Internal\",\n" +
            "                    \"position\": \"Delivery Manager\",\n" +
            "                    \"status\": \"active\",\n" +
            "                    \"unitName\": \"Storage Solutions\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 5,\n" +
            "                    \"recordNumber\": 111500005,\n" +
            "                    \"fullName\": \"Dennis Ritchie\",\n" +
            "                    \"telephone\": \"6900000000\",\n" +
            "                    \"workingPeriod\": \"1970-01-01 - 2000-01-01\",\n" +
            "                    \"contractType\": \"Company Internal\",\n" +
            "                    \"position\": \"Programmer\",\n" +
            "                    \"status\": \"inactive\",\n" +
            "                    \"unitName\": \"Storage Solutions\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"updates\": []\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 5,\n" +
            "            \"title\": \"New database5\",\n" +
            "            \"desc\": \"Integrate sql db5.\",\n" +
            "            \"difficulty\": \"EASY\",\n" +
            "            \"status\": \"New\",\n" +
            "            \"assignedEmployees\": [\n" +
            "                {\n" +
            "                    \"id\": 5,\n" +
            "                    \"recordNumber\": 111500005,\n" +
            "                    \"fullName\": \"Dennis Ritchie\",\n" +
            "                    \"telephone\": \"6900000000\",\n" +
            "                    \"workingPeriod\": \"1970-01-01 - 2000-01-01\",\n" +
            "                    \"contractType\": \"Company Internal\",\n" +
            "                    \"position\": \"Programmer\",\n" +
            "                    \"status\": \"inactive\",\n" +
            "                    \"unitName\": \"Storage Solutions\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 6,\n" +
            "                    \"recordNumber\": 111500006,\n" +
            "                    \"fullName\": \"Benedict Cucumber\",\n" +
            "                    \"telephone\": \"6988596340\",\n" +
            "                    \"workingPeriod\": \"2013-03-05 - present\",\n" +
            "                    \"contractType\": \"Company Internal\",\n" +
            "                    \"position\": \"Accountant\",\n" +
            "                    \"status\": \"active\",\n" +
            "                    \"unitName\": \"File Archiving\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"updates\": []\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static String json2 = "{\n" +
            "    \"taskResponse\": [\n" +
            "        {\n" +
            "            \"id\": 3,\n" +
            "            \"title\": \"New database\",\n" +
            "            \"desc\": \"Integrate sql db.\",\n" +
            "            \"difficulty\": \"EASY\",\n" +
            "            \"status\": \"New\",\n" +
            "            \"assignedEmployees\": [\n" +
            "                {\n" +
            "                    \"id\": 1,\n" +
            "                    \"recordNumber\": 111500001,\n" +
            "                    \"fullName\": \"Konstantinos Tsiaras\",\n" +
            "                    \"telephone\": \"6980429197\",\n" +
            "                    \"workingPeriod\": \"2019-11-02 - present\",\n" +
            "                    \"contractType\": \"Company Internal\",\n" +
            "                    \"position\": \"Programmer\",\n" +
            "                    \"status\": \"active\",\n" +
            "                    \"unitName\": \"Core Banking\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"updates\": []\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static String json3 = "{\n" +
            "    \"taskResponse\": [\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"title\": \"New Website\",\n" +
            "            \"desc\": \"Create a website for the product.\",\n" +
            "            \"difficulty\": \"HARD\",\n" +
            "            \"status\": \"New\",\n" +
            "            \"assignedEmployees\": [\n" +
            "                {\n" +
            "                    \"id\": 3,\n" +
            "                    \"recordNumber\": 111500003,\n" +
            "                    \"fullName\": \"Steve Rogers\",\n" +
            "                    \"telephone\": \"6912345678\",\n" +
            "                    \"workingPeriod\": \"2012-06-22 - present\",\n" +
            "                    \"contractType\": \"External\",\n" +
            "                    \"position\": \"Scrum Master\",\n" +
            "                    \"status\": \"active\",\n" +
            "                    \"unitName\": \"Payment\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"updates\": []\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 3,\n" +
            "            \"title\": \"New database\",\n" +
            "            \"desc\": \"Integrate sql db.\",\n" +
            "            \"difficulty\": \"EASY\",\n" +
            "            \"status\": \"New\",\n" +
            "            \"assignedEmployees\": [\n" +
            "                {\n" +
            "                    \"id\": 1,\n" +
            "                    \"recordNumber\": 111500001,\n" +
            "                    \"fullName\": \"Konstantinos Tsiaras\",\n" +
            "                    \"telephone\": \"6980429197\",\n" +
            "                    \"workingPeriod\": \"2019-11-02 - present\",\n" +
            "                    \"contractType\": \"Company Internal\",\n" +
            "                    \"position\": \"Programmer\",\n" +
            "                    \"status\": \"active\",\n" +
            "                    \"unitName\": \"Core Banking\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"updates\": []\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}
