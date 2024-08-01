package com.godwin.finalshellcreak.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FinalShellConfig {

    @JsonProperty("forwarding_auto_reconnect")
    private Boolean forwardingAutoReconnect;
    @JsonProperty("custom_size")
    private Boolean customSize;
    @JsonProperty("delete_time")
    private Integer deleteTime;
    @JsonProperty("secret_key_id")
    private String secretKeyId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("conection_type")
    private Integer conectionType;
    @JsonProperty("sort_time")
    private Integer sortTime;
    @JsonProperty("description")
    private String description;
    @JsonProperty("proxy_id")
    private String proxyId;
    @JsonProperty("authentication_type")
    private Integer authenticationType;
    @JsonProperty("drivestoredirect")
    private Boolean drivestoredirect;
    @JsonProperty("delete_key_sequence")
    private Integer deleteKeySequence;
    @JsonProperty("password")
    private String password;
    @JsonProperty("modified_time")
    private Long modifiedTime;
    @JsonProperty("host")
    private String host;
    @JsonProperty("accelerate")
    private Boolean accelerate;
    @JsonProperty("id")
    private String id;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("order")
    private Integer order;
    @JsonProperty("create_time")
    private Long createTime;
    @JsonProperty("port_forwarding_list")
    private List<?> portForwardingList;
    @JsonProperty("parent_update_time")
    private Integer parentUpdateTime;
    @JsonProperty("rename_time")
    private Long renameTime;
    @JsonProperty("backspace_key_sequence")
    private Integer backspaceKeySequence;
    @JsonProperty("fullscreen")
    private Boolean fullscreen;
    @JsonProperty("port")
    private Integer port;
    @JsonProperty("terminal_encoding")
    private String terminalEncoding;
    @JsonProperty("parent_id")
    private String parentId;
    @JsonProperty("exec_channel_enable")
    private Boolean execChannelEnable;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("name")
    private String name;
    @JsonProperty("access_time")
    private Long accessTime;
}