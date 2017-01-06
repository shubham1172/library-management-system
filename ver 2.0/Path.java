class Path {
    public static String getAdd()
    {
        String path = Entry.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(path.indexOf('/')+1,path.lastIndexOf('/'));
        path = path.replace("%20"," ");
        String val[] = path.split("/");
        path ="";
        for(int i=0; i<val.length; i++)
            path = path + val[i] + "\\";
        return path;
    }
}