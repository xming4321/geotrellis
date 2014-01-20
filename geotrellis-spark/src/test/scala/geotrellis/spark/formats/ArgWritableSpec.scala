package geotrellis.spark.formats

import geotrellis._
import geotrellis.TypeByte
import geotrellis.TypeDouble
import geotrellis.TypeFloat
import geotrellis.TypeInt
import geotrellis.TypeShort
import geotrellis.raster.ByteArrayRasterData
import geotrellis.raster.DoubleArrayRasterData
import geotrellis.raster.FloatArrayRasterData
import geotrellis.raster.IntArrayRasterData
import geotrellis.raster.ShortArrayRasterData
import geotrellis.raster.BitArrayRasterData
import geotrellis.TypeBit

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers
import org.scalatest.matchers.ShouldMatchers


class ArgWritableSpec extends FunSpec with MustMatchers with ShouldMatchers {
  describe("conversion from/to ArgWritable") {

    val cols = 2
    val rows = 2
    val size = cols * rows
    
    it("should convert from Array of ints to ArgWritable and back") {
      val expected = Array.ofDim[Int](size).fill(1)
      val actual = ArgWritable.toRasterData(ArgWritable.fromRasterData(IntArrayRasterData(expected, cols, rows)), TypeInt, cols, rows)
      expected should be(actual.asInstanceOf[IntArrayRasterData].array)
    }

    it("should convert from Array of shorts to ArgWritable and back") {
      val expected = Array.ofDim[Short](size).fill(1)
      val actual = ArgWritable.toRasterData(ArgWritable.fromRasterData(ShortArrayRasterData(expected, cols, rows)), TypeShort, cols, rows)
      expected should be(actual.asInstanceOf[ShortArrayRasterData].array)
    }

    it("should convert from Array of doubles to ArgWritable and back") {
      val expected = Array.ofDim[Double](size).fill(1)
      val actual = ArgWritable.toRasterData(ArgWritable.fromRasterData(DoubleArrayRasterData(expected, cols, rows)), TypeDouble, cols, rows)
      expected should be(actual.asInstanceOf[DoubleArrayRasterData].array)
    }

    it("should convert from Array of floats to ArgWritable and back") {
      val expected = Array.ofDim[Float](size).fill(1)
      val actual = ArgWritable.toRasterData(ArgWritable.fromRasterData(FloatArrayRasterData(expected, cols, rows)), TypeFloat, cols, rows)
      expected should be(actual.asInstanceOf[FloatArrayRasterData].array)
    }

    it("should convert from Array of bytes to ArgWritable and back") {
      val expected = Array.ofDim[Byte](size).fill(1)
      val actual = ArgWritable.toRasterData(ArgWritable.fromRasterData(ByteArrayRasterData(expected, cols, rows)), TypeByte, cols, rows)
      expected should be(actual.asInstanceOf[ByteArrayRasterData].array)
    }

    it("should convert from Array of bytes (actually, bit masks) to ArgWritable and back") {
      val expected = Array.ofDim[Byte](size).fill(1)
      
      // bit mask length is 8x4 since there are 4 bytes of length 8 bits each
      val cols = 8
      val rows = 4
      
      val actual = ArgWritable.toRasterData(ArgWritable.fromRasterData(BitArrayRasterData(expected, cols, rows)), TypeBit, cols, rows)
      expected should be(actual.asInstanceOf[BitArrayRasterData].array)
    }
  }
}