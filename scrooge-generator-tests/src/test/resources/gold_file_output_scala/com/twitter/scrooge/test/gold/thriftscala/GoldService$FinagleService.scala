/**
 * Generated by Scrooge
 *   version: ?
 *   rev: ?
 *   built at: ?
 */
package com.twitter.scrooge.test.gold.thriftscala

import com.twitter.finagle.{
  Filter => _,
  Service => finagle$Service,
  thrift => _,
  _
}
import com.twitter.finagle.stats.{NullStatsReceiver, StatsReceiver}
import com.twitter.finagle.thrift.RichServerParam
import com.twitter.io.Buf
import com.twitter.util.Future
import org.apache.thrift.protocol._
import org.apache.thrift.TApplicationException
import org.apache.thrift.transport.TMemoryInputTransport
import scala.collection.mutable.{HashMap => mutable$HashMap}

import scala.language.higherKinds


@javax.annotation.Generated(value = Array("com.twitter.scrooge.Compiler"))
class GoldService$FinagleService(
  iface: GoldService[Future],
  serverParam: RichServerParam
) extends com.twitter.finagle.Service[Array[Byte], Array[Byte]] {
  import GoldService._

  @deprecated("Use com.twitter.finagle.thrift.RichServerParam", "2017-08-16")
  def this(
    iface: GoldService[Future],
    protocolFactory: TProtocolFactory,
    stats: StatsReceiver = NullStatsReceiver,
    maxThriftBufferSize: Int = Thrift.param.maxThriftBufferSize,
    serviceName: String = "GoldService"
  ) = this(iface, RichServerParam(protocolFactory, serviceName, maxThriftBufferSize, stats))

  @deprecated("Use com.twitter.finagle.thrift.RichServerParam", "2017-08-16")
  def this(
    iface: GoldService[Future],
    protocolFactory: TProtocolFactory,
    stats: StatsReceiver,
    maxThriftBufferSize: Int
  ) = this(iface, protocolFactory, stats, maxThriftBufferSize, "GoldService")

  @deprecated("Use com.twitter.finagle.thrift.RichServerParam", "2017-08-16")
  def this(
    iface: GoldService[Future],
    protocolFactory: TProtocolFactory
  ) = this(iface, protocolFactory, NullStatsReceiver, Thrift.param.maxThriftBufferSize)

  def serviceName: String = serverParam.serviceName
  private[this] val filters = new Filter(serverParam)

  private[this] def protocolFactory: TProtocolFactory = serverParam.restrictedProtocolFactory

  protected val serviceMap: mutable$HashMap[String, finagle$Service[(TProtocol, Int), Array[Byte]]] = new mutable$HashMap[String, finagle$Service[(TProtocol, Int), Array[Byte]]]()

  protected def addService(name: String, service: finagle$Service[(TProtocol, Int), Array[Byte]]): Unit = {
    serviceMap(name) = service
  }

  final def apply(request: Array[Byte]): Future[Array[Byte]] = {
    val inputTransport = new TMemoryInputTransport(request)
    val iprot = protocolFactory.getProtocol(inputTransport)

    try {
      val msg = iprot.readMessageBegin()
      val service = serviceMap.get(msg.name)
      service match {
        case _root_.scala.Some(svc) =>
          svc((iprot, msg.seqid))
        case _ =>
          TProtocolUtil.skip(iprot, TType.STRUCT)
          Future.value(Buf.ByteArray.Owned.extract(
            filters.exception(msg.name, msg.seqid, TApplicationException.UNKNOWN_METHOD,
              "Invalid method name: '" + msg.name + "'")))
      }
    } catch {
      case e: Exception => Future.exception(e)
    }
  }
  // ---- end boilerplate.

  addService("doGreatThings", {
    val methodService = new finagle$Service[DoGreatThings.Args, DoGreatThings.SuccessType] {
      def apply(args: DoGreatThings.Args): Future[DoGreatThings.SuccessType] = {
        if (_root_.com.twitter.finagle.tracing.Trace.isActivelyTracing) {
          _root_.com.twitter.finagle.tracing.Trace.recordRpc("doGreatThings")
        }
        iface.doGreatThings(args.request)
      }
    }
  
    filters.doGreatThings.andThen(methodService)
  })
}