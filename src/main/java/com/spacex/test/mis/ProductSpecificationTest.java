package com.spacex.test.mis;

import com.spacex.util.JsonUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductSpecificationTest {

    public static void main(String[] args) {
        run();
    }

    public static void run() {

        ItemSpec itemSpec = new ItemSpec();

        SpecBO mainSpec = new SpecBO();

        Map<String, List<String>> definition = new HashMap<>();
        List<String> sizeList = Arrays.asList("大杯", "中杯", "小杯");
        List<String> hotColdList = Arrays.asList("冷", "热");
        List<String> addSugarList = Arrays.asList("加糖", "不加糖");

        definition.put("大小", sizeList);

        mainSpec.setDefinition(definition);

        Map<String, String> defaultSpec = new HashMap<>();
        defaultSpec.put("大小", sizeList.get(0));
        mainSpec.setDefaultSpec(defaultSpec);


        //minor spec
        SpecBO minorSpec = new SpecBO();
        Map<String, List<String>> definitionOfMinorSpec = new HashMap<>();
        definitionOfMinorSpec.put("冷热", hotColdList);
        definitionOfMinorSpec.put("加糖", addSugarList);

        Map<String, String> defaultSpecOfMinorSpec = new HashMap<>();
        defaultSpecOfMinorSpec.put("冷热", hotColdList.get(1));
        defaultSpecOfMinorSpec.put("加糖", addSugarList.get(0));

        minorSpec.setDefinition(definitionOfMinorSpec);
        minorSpec.setDefaultSpec(defaultSpecOfMinorSpec);

        itemSpec.setMainSpec(mainSpec);
        itemSpec.setMinorSpec(minorSpec);

        System.out.println(JsonUtil.toJson(itemSpec));

    }

    private static class ItemSpec {
        private SpecBO mainSpec;
        private SpecBO minorSpec;

        public SpecBO getMainSpec() {
            return mainSpec;
        }

        public void setMainSpec(SpecBO mainSpec) {
            this.mainSpec = mainSpec;
        }

        public SpecBO getMinorSpec() {
            return minorSpec;
        }

        public void setMinorSpec(SpecBO minorSpec) {
            this.minorSpec = minorSpec;
        }

        @Override
        public String toString() {
            return "ItemSpec{" +
                    "mainSpec=" + mainSpec +
                    ", minorSpec=" + minorSpec +
                    '}';
        }
    }


    private static class SpecBO {
        private Map<String, List<String>> definition;
        private Map<String, String> defaultSpec;

        public Map<String, List<String>> getDefinition() {
            return definition;
        }

        public void setDefinition(Map<String, List<String>> definition) {
            this.definition = definition;
        }

        public Map<String, String> getDefaultSpec() {
            return defaultSpec;
        }

        public void setDefaultSpec(Map<String, String> defaultSpec) {
            this.defaultSpec = defaultSpec;
        }

        @Override
        public String toString() {
            return "SpecBO{" +
                    "definition=" + definition +
                    ", defaultSpec=" + defaultSpec +
                    '}';
        }
    }

    private static class SkuSpecBO {
        private Map<String, String> specs;
        private Long ItemId;

        public Map<String, String> getSpecs() {
            return specs;
        }

        public void setSpecs(Map<String, String> specs) {
            this.specs = specs;
        }

        public Long getItemId() {
            return ItemId;
        }

        public void setItemId(Long itemId) {
            ItemId = itemId;
        }
    }

}
