// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Person.proto

package com.iscas.common.rpc.tools.grpc;

public final class PersonProto {
  private PersonProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_iscas_common_rpc_tools_grpc_test_MyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_iscas_common_rpc_tools_grpc_test_MyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_iscas_common_rpc_tools_grpc_test_MyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_iscas_common_rpc_tools_grpc_test_MyResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Person.proto\022$com.iscas.common.rpc.too" +
      "ls.grpc.test\"\035\n\tMyRequest\022\020\n\010username\030\001 " +
      "\001(\t\"\036\n\nMyResponse\022\020\n\010realname\030\002 \001(\t2\215\001\n\r" +
      "PersonService\022|\n\025GetRealNameByUsername\022/" +
      ".com.iscas.common.rpc.tools.grpc.test.My" +
      "Request\0320.com.iscas.common.rpc.tools.grp" +
      "c.test.MyResponse\"\000B5\n$com.iscas.common." +
      "rpc.tools.grpc.testB\013PersonProtoP\001b\006prot" +
      "o3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_iscas_common_rpc_tools_grpc_test_MyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_iscas_common_rpc_tools_grpc_test_MyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_iscas_common_rpc_tools_grpc_test_MyRequest_descriptor,
        new java.lang.String[] { "Username", });
    internal_static_com_iscas_common_rpc_tools_grpc_test_MyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_iscas_common_rpc_tools_grpc_test_MyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_iscas_common_rpc_tools_grpc_test_MyResponse_descriptor,
        new java.lang.String[] { "Realname", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
