package com.unisystems.registry;

public class StructureUtil
{
    private String[] structure = {"Company", "BusinessUnit", "Department", "Unit"};

    public boolean checkIfInStructure(String section)
    {
        for (String s : structure)
        {
            if (s.equalsIgnoreCase(section))
               return true;
        }
        return false;
    }
}
