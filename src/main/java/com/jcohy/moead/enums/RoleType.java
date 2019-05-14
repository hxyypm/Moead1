package com.jcohy.moead.enums;

public enum RoleType {


    Admin(0,"admin"),
    STUDENT(1,"student"),
    TEACHER(2,"teacher");

    private  Integer value;
    private  String name;
    RoleType(Integer value, String name){
        this.name=name;
        this.value=value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Integer getValueByName(String name){
        for(RoleType value: RoleType.values()){
            if(value.getName().equals(name)){
                return value.getValue();
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value){
        for(RoleType names: RoleType.values()){
            if(names.getValue().equals(value)){
                return names.getName();
            }
        }
        return null;
    }
}
