package com.example.tvintento2.placeholder;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final ArrayList<PlaceholderContent.ParcialholderItem> ITEMS = new ArrayList<PlaceholderContent.ParcialholderItem>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<Integer, ParcialholderItem> ITEM_MAP = new HashMap<Integer, ParcialholderItem>();

    private static final String TAG = PlaceholderContent.class.getSimpleName();

    private static final int COUNT = 25;

    /*static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createParcialholderItem(i));
        }
    }*/

    private static void addItem(PlaceholderContent.ParcialholderItem item) {
        ITEMS.add(item);
        //ITEM_MAP.put(item.indice, item);
    }

    private static PlaceholderContent.ParcialholderItem createParcialholderItem(JSONObject obj) {
        return new PlaceholderContent.ParcialholderItem(obj);
    }

    /*
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }*/

    /**
     * A placeholder item representing a piece of content.
     */
    public static class ParcialholderItem extends Throwable{

        private static final String TAG = ParcialholderItem.class.getSimpleName();
        private int id;
        private String imagen;
        private String nombre;
        private String matricula;
        private String curp;
        private String sexo;
        private int edad;

        public ParcialholderItem(JSONObject obj) {
            try {
                //this.setId(obj.has("id") ? Integer.parseInt(obj.getString("id")) : -1);
                //this.setImagen(obj.has("imagen") ? obj.getString("imagen") : "");
                this.setNombre(obj.has("nombre") ? obj.getString("nombre") : "");
                this.setMatricula(obj.has("matricula") ? obj.getString("matricula") : "");
                this.setCurp(obj.has("curp") ? obj.getString("curp") : "");
                this.setSexo(obj.has("sexo") ? obj.getString("sexo") : "");
                this.setEdad(obj.has("edad") ? Integer.parseInt(obj.getString("edad")) : -1);
                this.setImagen(obj.has("imagen") ? obj.getString("imagen") : "");
            } catch (Exception e) {
                //Log.e(getTAG(), e.getMessage());
                System.out.println(e.getMessage());
            }
        }

        public static ArrayList<ParcialholderItem> fromJson(JSONArray jsonObjects) {
            ITEMS.clear();
            for (int index = 0; index < jsonObjects.length(); index++) {
                try {
                    addItem((new ParcialholderItem(jsonObjects.getJSONObject(index))));
                } catch (JSONException e) {
                    Log.e(getTag(), e.getMessage());
                    e.printStackTrace();
                }
            }
            return ITEMS;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public String getCurp() {
            return curp;
        }

        public void setCurp(String curp) {
            this.curp = curp;
        }

        public String getSexo() {
            return sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public static String getTag() {
            return TAG;
        }
    }
}
