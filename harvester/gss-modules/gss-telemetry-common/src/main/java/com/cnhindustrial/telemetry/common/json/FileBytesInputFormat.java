package com.cnhindustrial.telemetry.common.json;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.flink.api.common.io.FileInputFormat;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.ResultTypeQueryable;
import org.apache.flink.core.fs.FileInputSplit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Read file content into byte array in one split.
 * For reading file formats that are considered un-splittable, like XML, JSON, etc.
 * This implementation is usable if the files are very small.
 */
public class FileBytesInputFormat extends FileInputFormat<byte[]> implements ResultTypeQueryable<byte[]> {

    private static final long serialVersionUID = 6929962367626422423L;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileBytesInputFormat.class);

    private boolean end;

    public FileBytesInputFormat() {
        unsplittable = true;
    }

    @Override
    public void open(FileInputSplit split) throws IOException {
        super.open(split);

        LOGGER.debug("Opening file input split {} [{}, {}]", split.getPath(), this.splitStart, this.splitLength);
        end = false;
    }

    @Override
    public byte[] nextRecord(byte[] reuse) throws IOException {
        byte[] bytes = IOUtils.toByteArray(stream);
        end = true;

        LOGGER.debug("Number of bytes read from file input split {}: {}", this.currentSplit.getPath(), bytes.length);
        return bytes;
    }

    @Override
    public boolean reachedEnd() {
        return end;
    }

    @Override
    public TypeInformation<byte[]> getProducedType() {
        return TypeInformation.of(new TypeHint<byte[]>() {});
    }
}
