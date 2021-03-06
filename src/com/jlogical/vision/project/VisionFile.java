package com.jlogical.vision.project;

import org.json.simple.JSONObject;

/**
 * Stores information for one file of code.
 */
public class VisionFile {
    /**
     * The name of the file.
     */
    private String name;

    /**
     * The code of the file.
     */
    private String code;

    /**
     * Creates a new VisionFile.
     * @param name the name of the file.
     * @param code the code of the file.
     */
    public VisionFile(String name, String code){
        this.name = name;
        this.code = code;
    }

    /**
     * @return the VisionFile from a JSon. Null if not compatible.
     */
    public static VisionFile fromJSon(JSONObject json){
        Object oName = json.get("name");
        if(oName == null){
            return null;
        }
        String name = (String) oName;
        Object oCode = json.get("code");
        if(oCode == null){
            return null;
        }
        String code = (String) oCode;
        return new VisionFile(name, code);
    }

    /**
     * @return the VisionFile in JSON format.
     */
    JSONObject toJSon(){
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("code", code);
        return obj;
    }

    @Override
    public String toString(){
        return "["+name+"]:\""+code+"\"";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
