// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: platform/api_user_phone.proto

package com.akaxin.proto.platform;

public final class ApiUserPhoneProto {
  private ApiUserPhoneProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface ApiUserPhoneRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:platform.ApiUserPhoneRequest)
      com.google.protobuf.MessageLiteOrBuilder {
  }
  /**
   * Protobuf type {@code platform.ApiUserPhoneRequest}
   */
  public  static final class ApiUserPhoneRequest extends
      com.google.protobuf.GeneratedMessageLite<
          ApiUserPhoneRequest, ApiUserPhoneRequest.Builder> implements
      // @@protoc_insertion_point(message_implements:platform.ApiUserPhoneRequest)
      ApiUserPhoneRequestOrBuilder {
    private ApiUserPhoneRequest() {
    }
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
    }

    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      memoizedSerializedSize = size;
      return size;
    }

    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    /**
     * Protobuf type {@code platform.ApiUserPhoneRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          ApiUserPhoneRequest, Builder> implements
        // @@protoc_insertion_point(builder_implements:platform.ApiUserPhoneRequest)
        com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequestOrBuilder {
      // Construct using com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest.newBuilder()
      private Builder() {
        super(DEFAULT_INSTANCE);
      }


      // @@protoc_insertion_point(builder_scope:platform.ApiUserPhoneRequest)
    }
    protected final Object dynamicMethod(
        MethodToInvoke method,
        Object arg0, Object arg1) {
      switch (method) {
        case NEW_MUTABLE_INSTANCE: {
          return new com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest();
        }
        case IS_INITIALIZED: {
          return DEFAULT_INSTANCE;
        }
        case MAKE_IMMUTABLE: {
          return null;
        }
        case NEW_BUILDER: {
          return new Builder();
        }
        case VISIT: {
          Visitor visitor = (Visitor) arg0;
          com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest other = (com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest) arg1;
          if (visitor == MergeFromVisitor
              .INSTANCE) {
          }
          return this;
        }
        case MERGE_FROM_STREAM: {
          com.google.protobuf.CodedInputStream input =
              (com.google.protobuf.CodedInputStream) arg0;
          com.google.protobuf.ExtensionRegistryLite extensionRegistry =
              (com.google.protobuf.ExtensionRegistryLite) arg1;
          try {
            boolean done = false;
            while (!done) {
              int tag = input.readTag();
              switch (tag) {
                case 0:
                  done = true;
                  break;
                default: {
                  if (!input.skipField(tag)) {
                    done = true;
                  }
                  break;
                }
              }
            }
          } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw new RuntimeException(e.setUnfinishedMessage(this));
          } catch (java.io.IOException e) {
            throw new RuntimeException(
                new com.google.protobuf.InvalidProtocolBufferException(
                    e.getMessage()).setUnfinishedMessage(this));
          } finally {
          }
        }
        case GET_DEFAULT_INSTANCE: {
          return DEFAULT_INSTANCE;
        }
        case GET_PARSER: {
          if (PARSER == null) {    synchronized (com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest.class) {
              if (PARSER == null) {
                PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
              }
            }
          }
          return PARSER;
        }
      }
      throw new UnsupportedOperationException();
    }


    // @@protoc_insertion_point(class_scope:platform.ApiUserPhoneRequest)
    private static final com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ApiUserPhoneRequest();
      DEFAULT_INSTANCE.makeImmutable();
    }

    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<ApiUserPhoneRequest> PARSER;

    public static com.google.protobuf.Parser<ApiUserPhoneRequest> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
  }

  public interface ApiUserPhoneResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:platform.ApiUserPhoneResponse)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     *绑定的手机号码
     * </pre>
     *
     * <code>optional string phone_id = 1;</code>
     */
    String getPhoneId();
    /**
     * <pre>
     *绑定的手机号码
     * </pre>
     *
     * <code>optional string phone_id = 1;</code>
     */
    com.google.protobuf.ByteString
        getPhoneIdBytes();

    /**
     * <pre>
     *+86
     * </pre>
     *
     * <code>optional string country_code = 2;</code>
     */
    String getCountryCode();
    /**
     * <pre>
     *+86
     * </pre>
     *
     * <code>optional string country_code = 2;</code>
     */
    com.google.protobuf.ByteString
        getCountryCodeBytes();
  }
  /**
   * Protobuf type {@code platform.ApiUserPhoneResponse}
   */
  public  static final class ApiUserPhoneResponse extends
      com.google.protobuf.GeneratedMessageLite<
          ApiUserPhoneResponse, ApiUserPhoneResponse.Builder> implements
      // @@protoc_insertion_point(message_implements:platform.ApiUserPhoneResponse)
      ApiUserPhoneResponseOrBuilder {
    private ApiUserPhoneResponse() {
      phoneId_ = "";
      countryCode_ = "";
    }
    public static final int PHONE_ID_FIELD_NUMBER = 1;
    private String phoneId_;
    /**
     * <pre>
     *绑定的手机号码
     * </pre>
     *
     * <code>optional string phone_id = 1;</code>
     */
    public String getPhoneId() {
      return phoneId_;
    }
    /**
     * <pre>
     *绑定的手机号码
     * </pre>
     *
     * <code>optional string phone_id = 1;</code>
     */
    public com.google.protobuf.ByteString
        getPhoneIdBytes() {
      return com.google.protobuf.ByteString.copyFromUtf8(phoneId_);
    }
    /**
     * <pre>
     *绑定的手机号码
     * </pre>
     *
     * <code>optional string phone_id = 1;</code>
     */
    private void setPhoneId(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      phoneId_ = value;
    }
    /**
     * <pre>
     *绑定的手机号码
     * </pre>
     *
     * <code>optional string phone_id = 1;</code>
     */
    private void clearPhoneId() {

      phoneId_ = getDefaultInstance().getPhoneId();
    }
    /**
     * <pre>
     *绑定的手机号码
     * </pre>
     *
     * <code>optional string phone_id = 1;</code>
     */
    private void setPhoneIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      phoneId_ = value.toStringUtf8();
    }

    public static final int COUNTRY_CODE_FIELD_NUMBER = 2;
    private String countryCode_;
    /**
     * <pre>
     *+86
     * </pre>
     *
     * <code>optional string country_code = 2;</code>
     */
    public String getCountryCode() {
      return countryCode_;
    }
    /**
     * <pre>
     *+86
     * </pre>
     *
     * <code>optional string country_code = 2;</code>
     */
    public com.google.protobuf.ByteString
        getCountryCodeBytes() {
      return com.google.protobuf.ByteString.copyFromUtf8(countryCode_);
    }
    /**
     * <pre>
     *+86
     * </pre>
     *
     * <code>optional string country_code = 2;</code>
     */
    private void setCountryCode(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      countryCode_ = value;
    }
    /**
     * <pre>
     *+86
     * </pre>
     *
     * <code>optional string country_code = 2;</code>
     */
    private void clearCountryCode() {

      countryCode_ = getDefaultInstance().getCountryCode();
    }
    /**
     * <pre>
     *+86
     * </pre>
     *
     * <code>optional string country_code = 2;</code>
     */
    private void setCountryCodeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      countryCode_ = value.toStringUtf8();
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!phoneId_.isEmpty()) {
        output.writeString(1, getPhoneId());
      }
      if (!countryCode_.isEmpty()) {
        output.writeString(2, getCountryCode());
      }
    }

    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (!phoneId_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeStringSize(1, getPhoneId());
      }
      if (!countryCode_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeStringSize(2, getCountryCode());
      }
      memoizedSerializedSize = size;
      return size;
    }

    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    /**
     * Protobuf type {@code platform.ApiUserPhoneResponse}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          ApiUserPhoneResponse, Builder> implements
        // @@protoc_insertion_point(builder_implements:platform.ApiUserPhoneResponse)
        com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponseOrBuilder {
      // Construct using com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse.newBuilder()
      private Builder() {
        super(DEFAULT_INSTANCE);
      }


      /**
       * <pre>
       *绑定的手机号码
       * </pre>
       *
       * <code>optional string phone_id = 1;</code>
       */
      public String getPhoneId() {
        return instance.getPhoneId();
      }
      /**
       * <pre>
       *绑定的手机号码
       * </pre>
       *
       * <code>optional string phone_id = 1;</code>
       */
      public com.google.protobuf.ByteString
          getPhoneIdBytes() {
        return instance.getPhoneIdBytes();
      }
      /**
       * <pre>
       *绑定的手机号码
       * </pre>
       *
       * <code>optional string phone_id = 1;</code>
       */
      public Builder setPhoneId(
          String value) {
        copyOnWrite();
        instance.setPhoneId(value);
        return this;
      }
      /**
       * <pre>
       *绑定的手机号码
       * </pre>
       *
       * <code>optional string phone_id = 1;</code>
       */
      public Builder clearPhoneId() {
        copyOnWrite();
        instance.clearPhoneId();
        return this;
      }
      /**
       * <pre>
       *绑定的手机号码
       * </pre>
       *
       * <code>optional string phone_id = 1;</code>
       */
      public Builder setPhoneIdBytes(
          com.google.protobuf.ByteString value) {
        copyOnWrite();
        instance.setPhoneIdBytes(value);
        return this;
      }

      /**
       * <pre>
       *+86
       * </pre>
       *
       * <code>optional string country_code = 2;</code>
       */
      public String getCountryCode() {
        return instance.getCountryCode();
      }
      /**
       * <pre>
       *+86
       * </pre>
       *
       * <code>optional string country_code = 2;</code>
       */
      public com.google.protobuf.ByteString
          getCountryCodeBytes() {
        return instance.getCountryCodeBytes();
      }
      /**
       * <pre>
       *+86
       * </pre>
       *
       * <code>optional string country_code = 2;</code>
       */
      public Builder setCountryCode(
          String value) {
        copyOnWrite();
        instance.setCountryCode(value);
        return this;
      }
      /**
       * <pre>
       *+86
       * </pre>
       *
       * <code>optional string country_code = 2;</code>
       */
      public Builder clearCountryCode() {
        copyOnWrite();
        instance.clearCountryCode();
        return this;
      }
      /**
       * <pre>
       *+86
       * </pre>
       *
       * <code>optional string country_code = 2;</code>
       */
      public Builder setCountryCodeBytes(
          com.google.protobuf.ByteString value) {
        copyOnWrite();
        instance.setCountryCodeBytes(value);
        return this;
      }

      // @@protoc_insertion_point(builder_scope:platform.ApiUserPhoneResponse)
    }
    protected final Object dynamicMethod(
        MethodToInvoke method,
        Object arg0, Object arg1) {
      switch (method) {
        case NEW_MUTABLE_INSTANCE: {
          return new com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse();
        }
        case IS_INITIALIZED: {
          return DEFAULT_INSTANCE;
        }
        case MAKE_IMMUTABLE: {
          return null;
        }
        case NEW_BUILDER: {
          return new Builder();
        }
        case VISIT: {
          Visitor visitor = (Visitor) arg0;
          com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse other = (com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse) arg1;
          phoneId_ = visitor.visitString(!phoneId_.isEmpty(), phoneId_,
              !other.phoneId_.isEmpty(), other.phoneId_);
          countryCode_ = visitor.visitString(!countryCode_.isEmpty(), countryCode_,
              !other.countryCode_.isEmpty(), other.countryCode_);
          if (visitor == MergeFromVisitor
              .INSTANCE) {
          }
          return this;
        }
        case MERGE_FROM_STREAM: {
          com.google.protobuf.CodedInputStream input =
              (com.google.protobuf.CodedInputStream) arg0;
          com.google.protobuf.ExtensionRegistryLite extensionRegistry =
              (com.google.protobuf.ExtensionRegistryLite) arg1;
          try {
            boolean done = false;
            while (!done) {
              int tag = input.readTag();
              switch (tag) {
                case 0:
                  done = true;
                  break;
                default: {
                  if (!input.skipField(tag)) {
                    done = true;
                  }
                  break;
                }
                case 10: {
                  String s = input.readStringRequireUtf8();

                  phoneId_ = s;
                  break;
                }
                case 18: {
                  String s = input.readStringRequireUtf8();

                  countryCode_ = s;
                  break;
                }
              }
            }
          } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw new RuntimeException(e.setUnfinishedMessage(this));
          } catch (java.io.IOException e) {
            throw new RuntimeException(
                new com.google.protobuf.InvalidProtocolBufferException(
                    e.getMessage()).setUnfinishedMessage(this));
          } finally {
          }
        }
        case GET_DEFAULT_INSTANCE: {
          return DEFAULT_INSTANCE;
        }
        case GET_PARSER: {
          if (PARSER == null) {    synchronized (com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse.class) {
              if (PARSER == null) {
                PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
              }
            }
          }
          return PARSER;
        }
      }
      throw new UnsupportedOperationException();
    }


    // @@protoc_insertion_point(class_scope:platform.ApiUserPhoneResponse)
    private static final com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ApiUserPhoneResponse();
      DEFAULT_INSTANCE.makeImmutable();
    }

    public static com.akaxin.proto.platform.ApiUserPhoneProto.ApiUserPhoneResponse getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<ApiUserPhoneResponse> PARSER;

    public static com.google.protobuf.Parser<ApiUserPhoneResponse> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}
