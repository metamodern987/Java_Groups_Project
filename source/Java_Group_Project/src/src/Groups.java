/*
   Igor Dmitriev
   5.11.2019
   Класс Groups, описывающий поля и методы для доступа к
   данным полям
 */

public class Groups {

    private int groupId; // поле - ид группы

    private String nameGroup;   // поле - Имя группы

    private String curator;    //поле - Куратор группы

    private String speciality; //поле - Специальность

//*****************************************************
    public int getGroupId(){   //get-set для groupId

        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
//******************************************************
    public String getNameGroup(){   //get-set для nameGroup

        return nameGroup;
    }
    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }
//***************************************************
     public String getCurator(){   //get-set для curator
      return curator;
     }
    public void setCurator(String curator) {
        this.curator = curator;
    }
    //***************************************************
    public String getSpeciality(){   //get-set для speciality
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String toString() {
        return nameGroup;
    }
}
