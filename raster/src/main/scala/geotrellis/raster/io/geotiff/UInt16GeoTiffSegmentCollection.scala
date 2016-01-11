package geotrellis.raster.io.geotiff

import geotrellis.raster._

trait UInt16GeoTiffSegmentCollection extends GeoTiffSegmentCollection {
  type T = UInt16GeoTiffSegment

  val bandType = UInt16BandType
  val noDataValue: Double

  val createSegment: Int => UInt16GeoTiffSegment =
    { i: Int => new UInt16GeoTiffSegment(getDecompressedBytes(i), noDataValue) }
}

trait RawUInt16GeoTiffSegmentCollection extends GeoTiffSegmentCollection {
  type T = RawUInt16GeoTiffSegment

  val bandType = UInt16BandType

  val createSegment: Int => RawUInt16GeoTiffSegment =
    { i: Int => new RawUInt16GeoTiffSegment(getDecompressedBytes(i)) }
}
