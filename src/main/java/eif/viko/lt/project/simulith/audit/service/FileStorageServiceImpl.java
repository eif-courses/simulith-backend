package eif.viko.lt.project.simulith.audit.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.pcloud.sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Autowired
    Gson gson;
    @Override
    public String printFolder() {

        List<ConvertedObject> list = new ArrayList<>();
        String token = "36F4ZMC2hNi6BcQuZX4x3c7Z926hUvlrMQF3FhnOQLObYSqyjzhV";
        ApiClient apiClient = PCloudSdk.newClientBuilder()
                .apiHost("eapi.pcloud.com")
                .authenticator(Authenticators.newOAuthAuthenticator(token))
                .create();
        try {
            RemoteFolder folder = apiClient.listFolder(RemoteFolder.ROOT_FOLDER_ID).execute();
            for (RemoteEntry entry : folder.children()) {
                list.add(new ConvertedObject("name", entry.name()));
                list.add(new ConvertedObject("created", String.valueOf(entry.created())));
                list.add(new ConvertedObject("last_modified", String.valueOf(entry.lastModified())));
                list.add(new ConvertedObject("total_sizes", entry.isFile() ? String.valueOf(entry.asFile().size()) : "-"));
                //System.out.format("%s | Created:%s | Modified: %s | size:%s\n", entry.name(), entry.created(), entry.lastModified(), entry.isFile() ? String.valueOf(entry.asFile().size()) : "-");
            }
            // System.out.println(jsonString);
            return gson.toJson(list);
        } catch (IOException | ApiError e) {
            e.printStackTrace();
            apiClient.shutdown();
        }
        return null;
    }
    static class ConvertedObject{
        private String key;
        private String value;

        public ConvertedObject(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "ConvertedObject{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
