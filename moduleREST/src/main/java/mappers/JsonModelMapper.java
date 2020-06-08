package mappers;

import java.util.ArrayList;
import java.util.List;

public interface JsonModelMapper<JSON, MODEL> {

    JSON convertToJson(MODEL model);

    default List<JSON> convertToJson(List<MODEL> models) {
        List<JSON> result = new ArrayList<>();
        for (MODEL model : models) {
            result.add(convertToJson(model));
        }
        return result;
    }

    MODEL convertToModel(JSON json);

    default List<MODEL> convertToModel(List<JSON> jsons) {
        List<MODEL> result = new ArrayList<>();
        for (JSON json : jsons) {
            result.add(convertToModel(json));
        }
        return result;
    }

}
